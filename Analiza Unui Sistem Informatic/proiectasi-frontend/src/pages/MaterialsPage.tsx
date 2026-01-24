import { useEffect, useMemo, useState } from 'react';
import type { Course, Material } from '../types';
import { getAllCourses } from '../api/coursesApi';
import { createMaterial, deleteMaterial, getMaterialsByCourse, updateMaterial } from '../api/materialsApi';

type MaterialFormState = {
  id?: string;
  courseId: string;
  name: string;
  fileName: string;
  dataBase64: string;
};

function emptyMaterialForm(courseId: string): MaterialFormState {
  return { courseId, name: '', fileName: '', dataBase64: '' };
}

function base64ToBlob(base64: string, mimeType: string): Blob {
  const byteChars = atob(base64);
  const byteNumbers = new Array(byteChars.length);
  for (let i = 0; i < byteChars.length; i++) byteNumbers[i] = byteChars.charCodeAt(i);
  return new Blob([new Uint8Array(byteNumbers)], { type: mimeType });
}

function guessMime(fileName: string): string {
  const n = (fileName || '').toLowerCase();
  if (n.endsWith('.pdf')) return 'application/pdf';
  if (n.endsWith('.png')) return 'image/png';
  if (n.endsWith('.jpg') || n.endsWith('.jpeg')) return 'image/jpeg';
  if (n.endsWith('.txt')) return 'text/plain';
  if (n.endsWith('.doc')) return 'application/msword';
  if (n.endsWith('.docx')) return 'application/vnd.openxmlformats-officedocument.wordprocessingml.document';
  return 'application/octet-stream';
}

export default function MaterialsPage() {
  const [courses, setCourses] = useState<Course[]>([]);
  const [selectedCourseId, setSelectedCourseId] = useState<string>('');
  const [materials, setMaterials] = useState<Material[]>([]);

  const [loadingCourses, setLoadingCourses] = useState(true);
  const [loadingMaterials, setLoadingMaterials] = useState(false);
  const [error, setError] = useState('');

  const [mode, setMode] = useState<'create' | 'edit'>('create');
  const [form, setForm] = useState<MaterialFormState>(emptyMaterialForm(''));
  const [saving, setSaving] = useState(false);
  const [readingFile, setReadingFile] = useState(false);

  const canSave = useMemo(() => {
    return (
      form.courseId.trim() &&
      form.name.trim() &&
      form.fileName.trim() &&
      form.dataBase64.trim()
    );
  }, [form]);

  async function loadCourses() {
    setLoadingCourses(true);
    setError('');
    try {
      const data = await getAllCourses();
      setCourses(data);

      const firstId = data.find((c) => (c.id || '').trim().length > 0)?.id || '';
      setSelectedCourseId((prev) => prev || firstId);
    } catch (e: any) {
      setError(e?.response?.data?.message || e?.message || 'Eroare la încărcarea cursurilor.');
    } finally {
      setLoadingCourses(false);
    }
  }

  async function loadMaterials(courseId: string) {
    if (!courseId) {
      setMaterials([]);
      return;
    }
    setLoadingMaterials(true);
    setError('');
    try {
      const data = await getMaterialsByCourse(courseId);
      setMaterials(data);
    } catch (e: any) {
      setError(e?.response?.data?.message || e?.message || 'Eroare la încărcarea materialelor.');
    } finally {
      setLoadingMaterials(false);
    }
  }

  useEffect(() => {
    loadCourses();
  }, []);

  useEffect(() => {
    setForm((s) => ({ ...s, courseId: selectedCourseId }));
    setMode('create');
    setForm(emptyMaterialForm(selectedCourseId));
    loadMaterials(selectedCourseId);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [selectedCourseId]);

  function startCreate() {
    setMode('create');
    setForm(emptyMaterialForm(selectedCourseId));
  }

  function startEdit(m: Material) {
    setMode('edit');
    setForm({
      id: m.id,
      courseId: m.courseId,
      name: m.name ?? '',
      fileName: (m.fileName ?? '') as string,
      dataBase64: (m.data ?? '') as string,
    });
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }

  async function onPickFile(file: File | null) {
    if (!file) return;
    setReadingFile(true);
    setError('');

    try {
      const buffer = await file.arrayBuffer();
      const bytes = new Uint8Array(buffer);
      let binary = '';
      for (let i = 0; i < bytes.length; i++) binary += String.fromCharCode(bytes[i]);
      const b64 = btoa(binary);

      setForm((s) => ({
        ...s,
        fileName: file.name,
        dataBase64: b64,
      }));
    } catch (e: any) {
      setError(e?.message || 'Nu am putut citi fișierul.');
    } finally {
      setReadingFile(false);
    }
  }

  async function onSubmit(e: React.FormEvent) {
    e.preventDefault();
    if (!canSave) return;

    setSaving(true);
    setError('');
    try {
      const payload: Material = {
        id: form.id,
        courseId: form.courseId.trim(),
        name: form.name.trim(),
        fileName: form.fileName.trim(),
        data: form.dataBase64.trim(),
      };

      if (mode === 'create') {
        await createMaterial(payload);
      } else {
        if (!form.id) {
          setError('Nu există id pentru update material.');
          return;
        }
        await updateMaterial(payload);
      }

      await loadMaterials(selectedCourseId);
      startCreate();
    } catch (e: any) {
      setError(e?.response?.data?.message || e?.message || 'Eroare la salvare material.');
    } finally {
      setSaving(false);
    }
  }

  async function onDelete(materialId?: string) {
    if (!materialId) return;
    const ok = window.confirm('Ștergi materialul?');
    if (!ok) return;

    setError('');
    try {
      await deleteMaterial(materialId);
      await loadMaterials(selectedCourseId);
      if (mode === 'edit' && form.id === materialId) startCreate();
    } catch (e: any) {
      setError(e?.response?.data?.message || e?.message || 'Eroare la ștergere material.');
    }
  }

  function onDownload(m: Material) {
    const base64 = (m.data || '').toString();
    const fileName = (m.fileName || 'fisier').toString();
    if (!base64.trim()) {
      setError('Materialul nu are data (fișier) în DB.');
      return;
    }

    const mime = guessMime(fileName);
    const blob = base64ToBlob(base64, mime);
    const url = URL.createObjectURL(blob);

    const a = document.createElement('a');
    a.href = url;
    a.download = fileName;
    document.body.appendChild(a);
    a.click();
    a.remove();

    URL.revokeObjectURL(url);
  }

  const selectedCourseTitle =
    courses.find((c) => c.id === selectedCourseId)?.title || '(curs necunoscut)';

  return (
    <div className="grid">
      <section className="card">
        <div className="card-head">
          <h2 className="card-title">Materiale</h2>
          <button className="btn" onClick={loadCourses} disabled={loadingCourses}>
            Refresh cursuri
          </button>
        </div>

        {error ? <div className="alert">{error}</div> : null}

        <div className="row">
          <label className="label">Alege curs</label>
          <select
            className="input"
            value={selectedCourseId}
            onChange={(e) => setSelectedCourseId(e.target.value)}
            disabled={loadingCourses}
          >
            <option value="" disabled>
              {loadingCourses ? 'Se încarcă...' : 'Selectează un curs'}
            </option>
            {courses.map((c) => (
              <option key={c.id || c.title} value={c.id || ''} disabled={!c.id}>
                {c.title} {c.id ? '' : '(fără id)'}
              </option>
            ))}
          </select>
          <div className="muted">
            Selectat: <span className="mono">{selectedCourseTitle}</span>
          </div>
        </div>
      </section>

      <section className="card">
        <h2 className="card-title">{mode === 'create' ? 'Adaugă material' : 'Editează material'}</h2>

        <form onSubmit={onSubmit} className="form">
          <div className="row">
            <label className="label">Nume material</label>
            <input
              className="input"
              value={form.name}
              onChange={(e) => setForm((s) => ({ ...s, name: e.target.value }))}
              placeholder="ex: Curs 1 - PDF"
              autoComplete="off"
              disabled={!selectedCourseId}
            />
          </div>

          <div className="row">
            <label className="label">Fișier</label>
            <input
              className="input"
              type="file"
              onChange={(e) => onPickFile(e.target.files?.item(0) || null)}
              disabled={!selectedCourseId || saving || readingFile}
            />
            <div className="muted">
              Selectat: <span className="mono">{form.fileName || '(nimic)'}</span>
              {readingFile ? ' • citesc fișierul...' : null}
            </div>
          </div>

          <div className="row">
            <label className="label">CourseId (auto)</label>
            <input className="input mono" value={form.courseId} readOnly />
          </div>

          <div className="actions">
            <button className="btn primary" type="submit" disabled={!canSave || saving || readingFile || !selectedCourseId}>
              {saving ? 'Salvez...' : mode === 'create' ? 'Creează' : 'Update'}
            </button>
            <button className="btn" type="button" onClick={startCreate} disabled={saving || readingFile || !selectedCourseId}>
              Reset
            </button>
          </div>
        </form>
      </section>

      <section className="card">
        <div className="card-head">
          <h2 className="card-title">Materiale pentru curs</h2>
          <button className="btn" onClick={() => loadMaterials(selectedCourseId)} disabled={loadingMaterials || !selectedCourseId}>
            Refresh
          </button>
        </div>

        {loadingMaterials ? <div className="muted">Se încarcă...</div> : null}
        {!loadingMaterials && materials.length === 0 ? (
          <div className="muted">Nu există materiale pentru cursul selectat.</div>
        ) : null}

        <div className="list">
          {materials.map((m) => (
            <div key={m.id || `${m.name}-${m.fileName || ''}`} className="list-item">
              <div className="list-main">
                <div className="list-title">{m.name}</div>
                <div className="list-sub">
                  {m.fileName ? <span className="pill">Fișier: {m.fileName}</span> : null}
                </div>
                <div className="list-desc">
                  {m.data ? <span className="muted">Fișier în DB: da</span> : <span className="muted">Fișier în DB: nu</span>}
                </div>
              </div>

              <div className="list-actions">
                <button className="btn" onClick={() => startEdit(m)}>
                  Edit
                </button>
                <button className="btn" onClick={() => onDownload(m)} disabled={!m.data || !m.fileName}>
                  Download
                </button>
                <button className="btn danger" onClick={() => onDelete(m.id)}>
                  Delete
                </button>
              </div>
            </div>
          ))}
        </div>
      </section>
    </div>
  );
}
