// DOM Elements
const uploadBtn = document.getElementById('uploadBtn');
const uploadInput = document.getElementById('uploadInput');
const videoGallery = document.getElementById('videoGallery');

const shortsBtn = document.getElementById('shortsBtn');
const shortsInput = document.getElementById('shortsInput');
const shortsGallery = document.getElementById('shortsGallery');

const signInOverlay = document.getElementById('signInOverlay');
const signInBtn = document.getElementById('signInBtn');
const usernameInput = document.getElementById('usernameInput');
const userWelcome = document.getElementById('userWelcome');
const logoutBtn = document.getElementById('logoutBtn');

let currentUser = null;

// On page load: check for saved user
window.addEventListener('DOMContentLoaded', () => {
  const savedUser = localStorage.getItem('mytubeUser');
  if (savedUser) {
    currentUser = savedUser;
    signInOverlay.style.display = 'none';
    userWelcome.textContent = `Hi, ${currentUser}`;
  }
});

// Sign In
signInBtn.addEventListener('click', () => {
  const username = usernameInput.value.trim();
  if (!username) return alert('Please enter your name.');
  
  currentUser = username;
  localStorage.setItem('mytubeUser', username);
  signInOverlay.style.display = 'none';
  userWelcome.textContent = `Hi, ${currentUser}`;
});

// Allow Enter key for sign-in
usernameInput.addEventListener('keydown', (e) => {
  if (e.key === 'Enter') signInBtn.click();
});

// Logout
logoutBtn.addEventListener('click', () => {
  localStorage.removeItem('mytubeUser');
  location.reload();
});

// Upload button triggers file input
uploadBtn.addEventListener('click', () => {
  if (!currentUser) return alert("Please sign in to upload");
  uploadInput.click();
});

shortsBtn.addEventListener('click', () => {
  if (!currentUser) return alert("Please sign in to upload");
  shortsInput.click();
});

// Handle video uploads
uploadInput.addEventListener('change', (e) => {
  const file = e.target.files[0];
  if (!file || !file.type.startsWith('video/')) return;

  const videoURL = URL.createObjectURL(file);

  const card = document.createElement('div');
  card.classList.add('video-card');
  card.innerHTML = `
    <div class="video-wrapper">
      <video controls src="${videoURL}"></video>
    </div>
    <div class="video-info">
      <h4>${file.name}</h4>
      <p>Uploaded just now</p>
    </div>
  `;

  if (videoGallery.querySelector('p')) videoGallery.innerHTML = '';
  videoGallery.appendChild(card);
});

// Handle shorts uploads
shortsInput.addEventListener('change', (e) => {
  const file = e.target.files[0];
  if (!file || !file.type.startsWith('video/')) return;

  const videoURL = URL.createObjectURL(file);

  const short = document.createElement('div');
  short.classList.add('short-card');
  short.innerHTML = `
    <video src="${videoURL}" autoplay loop muted playsinline></video>
    <div class="short-info">${file.name}</div>
  `;

  if (shortsGallery.querySelector('p')) shortsGallery.innerHTML = '';
  shortsGallery.appendChild(short);
});
