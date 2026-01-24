export type Course = {
  id?: string;
  title: string;
  description: string;
  creationDate?: string | null;
};

export type Material = {
  id?: string;
  courseId: string;
  name: string;
  fileName?: string | null;

  // backend: byte[]; în JSON îl trimitem/primim ca base64 string (fără prefix "data:...")
  data?: string | null;
};
