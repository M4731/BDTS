<template>
  <section class="home-hero">
    <div class="hero-card">
      <span class="badge">Magazin Online</span>
      <h1>Vue DEMO App</h1>
    </div>
    <div class="hero-details">
      <div class="detail-card">
        <h3>Design modern</h3>
        <p>Temă Vue cu accente verzi și galbene, pe un layout clar și aerisit.</p>
      </div>
      <div class="detail-card">
        <h3>Responsive</h3>
        <p>Funcționează foarte bine pe ecrane mici și pe desktop, fără elemente complicate.</p>
      </div>
      <div class="detail-card">
        <h3>Gestionare produse</h3>
        <p>Adaugă, editează și șterge produse rapid din interfața ta.</p>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: 'HomeView'
}
</script>

<style scoped>
.home-hero {
  display: grid;
  gap: 24px;
  padding: 28px 0;
}
.hero-card,
.detail-card {
  background: white;
  border: 1px solid rgba(15, 23, 42, 0.08);
  border-radius: 18px;
  padding: 28px;
  box-shadow: 0 16px 34px rgba(15, 23, 42, 0.06);
}
.hero-card {
  display: grid;
  gap: 18px;
}
.badge {
  display: inline-block;
  padding: 8px 14px;
  border-radius: 999px;
  background: #42b883;
  color: white;
  font-weight: 700;
  font-size: 0.9rem;
}
.hero-card h1 {
  margin: 0;
  font-size: clamp(2rem, 3.5vw, 3.2rem);
  line-height: 1.05;
  color: #0f172a;
}
.hero-card p {
  margin: 0;
  color: #475569;
  max-width: 720px;
}
.hero-actions {
  display: flex;
  flex-wrap: wrap;
  gap: 14px;
}
.primary-btn,
.secondary-btn {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  padding: 12px 20px;
  border-radius: 999px;
  text-decoration: none;
  font-weight: 700;
}
.primary-btn {
  background: #42b883;
  color: white;
}
.secondary-btn {
  background: #ffd166;
  color: #0f172a;
}
.detail-card h3 {
  margin: 0 0 10px;
  font-size: 1.1rem;
}
.detail-card p {
  margin: 0;
  color: #475569;
}
.hero-details {
  display: grid;
  grid-template-columns: repeat(3, minmax(0, 1fr));
  gap: 18px;
}
@media (max-width: 860px) {
  .hero-details {
    grid-template-columns: 1fr;
  }
}
</style>