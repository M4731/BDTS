import { NavLink, Outlet } from 'react-router-dom';

export default function Layout() {
  return (
    <div className="app">
      <header className="topbar">
        <div className="brand">Proiect ASI</div>

        <nav className="nav">
          <NavLink
            to="/courses"
            className={({ isActive }) => (isActive ? 'nav-link active' : 'nav-link')}
          >
            Cursuri
          </NavLink>

          <NavLink
            to="/materials"
            className={({ isActive }) => (isActive ? 'nav-link active' : 'nav-link')}
          >
            Materiale
          </NavLink>
        </nav>
      </header>

      <main className="container">
        <Outlet />
      </main>

      <footer className="footer">
        API: http://localhost:8080
      </footer>
    </div>
  );
}
