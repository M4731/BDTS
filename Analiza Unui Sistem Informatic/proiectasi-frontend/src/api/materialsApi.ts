import { http } from './http';
import type { Material } from '../types.ts';

export async function getMaterialsByCourse(courseId: string): Promise<Material[]> {
  const res = await http.get<Material[]>(
    `/api/materials/getByCourse/${encodeURIComponent(courseId)}`
  );
  return res.data;
}

export async function getMaterialById(materialId: string): Promise<Material> {
  const res = await http.get<Material>(`/api/materials/${encodeURIComponent(materialId)}`);
  return res.data;
}

export async function createMaterial(payload: Material): Promise<Material> {
  const res = await http.post<Material>('/api/materials', payload);
  return res.data;
}

export async function updateMaterial(payload: Material): Promise<Material> {
  const res = await http.put<Material>('/api/materials', payload);
  return res.data;
}

export async function deleteMaterial(materialId: string): Promise<void> {
  await http.delete(`/api/materials/${encodeURIComponent(materialId)}`);
}
