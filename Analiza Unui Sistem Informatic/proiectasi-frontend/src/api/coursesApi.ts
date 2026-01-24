import { http } from './http';
import type { Course } from '../types.ts';

export async function getAllCourses(): Promise<Course[]> {
  const res = await http.get<Course[]>('/api/courses');
  return res.data;
}

export async function getCourseById(id: string): Promise<Course> {
  const res = await http.get<Course>(`/api/courses/${encodeURIComponent(id)}`);
  return res.data;
}

export async function createCourse(payload: Course): Promise<Course> {
  const res = await http.post<Course>('/api/courses', payload);
  return res.data;
}

export async function updateCourse(id: string, payload: Course): Promise<Course> {
  const res = await http.put<Course>(`/api/courses/${encodeURIComponent(id)}`, payload);
  return res.data;
}

export async function deleteCourse(id: string): Promise<void> {
  await http.delete(`/api/courses/${encodeURIComponent(id)}`);
}
