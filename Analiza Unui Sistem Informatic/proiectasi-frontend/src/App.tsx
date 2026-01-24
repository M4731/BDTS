import { Navigate, Route, Routes } from 'react-router-dom';
import Layout from './components/Layout.tsx';
import CoursesPage from './pages/CoursesPage.tsx';
import CourseDetailsPage from './pages/CourseDetailsPage.tsx';
import MaterialsPage from './pages/MaterialsPage.tsx';

export default function App() {
  return (
    <Routes>
      <Route element={<Layout />}>
        <Route path="/" element={<Navigate to="/courses" replace />} />
        <Route path="/courses" element={<CoursesPage />} />
        <Route path="/courses/:id" element={<CourseDetailsPage />} />
        <Route path="/materials" element={<MaterialsPage />} />
      </Route>
      <Route path="*" element={<Navigate to="/courses" replace />} />
    </Routes>
  );
}
