import { useEffect, useMemo, useState } from 'react';
import type { Course } from '../types';
import { createCourse, deleteCourse, getAllCourses, updateCourse } from '../api/coursesApi';

type CourseFormState = {
  id?: string;
  title: string;
  description: string;
};

function emptyCourseForm(): CourseFormState {
  return { title: '', description: '' };
}

export default function CoursesPage() {
  const [courses, setCourses] = useState<Course[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string>('');

  const [mode, setMode] = useState<'create' | 'edit'>('create');
  const [form, setForm] = useState<CourseFormState>(emptyCourseForm());
  const [saving, setSaving] = useState(false);

  const canSave = useMemo(() => form.title.trim().length > 0 && form.description.trim().length > 0, [form]);

  async function load() {
    setLoading(true);
    setError('');
    try {
      const data = await getAllCourses();
      setCourses(data);
    } catch (e: any) {
      setError(e?.response?.data?.message || e?.message || 'Eroare la încărcarea cursurilor.');
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {
    load();
  }, []);

  function startCreate() {
    setMode('create');
    setForm(emptyCourseForm());
  }

  function startEdit(c: Course) {
    setMode('edit');
    setForm({
      id: c.id,
      title: c.title ?? '',
      description: c.description ?? '',
    });
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }

  async function onSubmit(e: React.FormEvent) {
    e.preventDefault();
    if (!canSave) return;

    setSaving(true);
    setError('');

    try {
      const payload: Course = {
        id: form.id,
        title: form.title.trim(),
        description: form.description.trim(),
      };

      if (mode === 'create') {
        await createCourse(payload);
      } else {
        if (!form.id) {
          setError('Nu există id pentru update.');
          return;
        }
        await updateCourse(form.id, payload);
      }

      await load();
      startCreate();
    } catch (e: any) {
      setError(e?.response?.data?.message || e?.message || 'Eroare la salvare.');
    } finally {
      setSaving(false);
    }
  }

  async function onDelete(id?: string) {
    if (!id) return;
    const ok = window.confirm('Ștergi cursul? Se vor șterge și materialele lui.');
    if (!ok) return;

    setError('');
    try {
      await deleteCourse(id);
      await load();
      if (mode === 'edit' && form.id === id) startCreate();
    } catch (e: any) {
      setError(e?.response?.data?.message || e?.message || 'Eroare la ștergere.');
    }
  }

  return (
    <div className="grid">
      <section className="card">
        <h2 className="card-title">{mode === 'create' ? 'Adaugă curs' : 'Editează curs'}</h2>

        {error ? <div className="alert">{error}</div> : null}

        <form onSubmit={onSubmit} className="form">
          <div className="row">
            <label className="label">Titlu</label>
            <input
              className="input"
              value={form.title}
              onChange={(e) => setForm((s) => ({ ...s, title: e.target.value }))}
              placeholder="ex: Java Basics"
              autoComplete="off"
            />
          </div>

          <div className="row">
            <label className="label">Descriere</label>
            <textarea
              className="textarea"
              value={form.description}
              onChange={(e) => setForm((s) => ({ ...s, description: e.target.value }))}
              placeholder="ex: Introducere în Java + OOP"
              rows={5}
            />
          </div>

          <div className="actions">
            <button className="btn primary" type="submit" disabled={!canSave || saving}>
              {saving ? 'Salvez...' : mode === 'create' ? 'Creează' : 'Update'}
            </button>
            <button className="btn" type="button" onClick={startCreate} disabled={saving}>
              Reset
            </button>
          </div>
        </form>
      </section>

      <section className="card">
        <div className="card-head">
          <h2 className="card-title">Cursuri</h2>
          <button className="btn" onClick={load} disabled={loading}>
            Refresh
          </button>
        </div>

        {loading ? <div className="muted">Se încarcă...</div> : null}
        {!loading && courses.length === 0 ? <div className="muted">Nu există cursuri.</div> : null}

        <div className="list">
          {courses.map((c) => (
            <div key={c.id || `${c.title}-${c.creationDate || ''}`} className="list-item">
              <div className="list-main">
                <div className="list-title">{c.title}</div>
                <div className="list-sub">
                  {c.creationDate ? <span className="pill">Creat: {new Date(c.creationDate).toLocaleString()}</span> : null}
                </div>
                <div className="list-desc">{c.description}</div>
              </div>

              <div className="list-actions">
                <button className="btn" onClick={() => startEdit(c)}>
                  Edit
                </button>
                <button className="btn danger" onClick={() => onDelete(c.id)}>
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
