// suatchieu.js
const API_BASE = '/api/suatchieu';

async function fetchAll() {
  const res = await fetch(API_BASE);
  return res.json();
}

function formatDateTime(dtStr) {
  const d = new Date(dtStr);
  return d.toLocaleString('vi-VN');
}

// ------ ADMIN PAGE ------

async function renderAdminTable() {
  const list = await fetchAll();
  const tbody = document.querySelector('#sc-table tbody');
  tbody.innerHTML = '';
  list.forEach(sc => {
    const tr = document.createElement('tr');
    tr.innerHTML = `
      <td>${sc.maSuatChieu}</td>
      <td>${sc.movie}</td>
      <td>${formatDateTime(sc.time)}</td>
      <td>${sc.room}</td>
      <td>
        <button class="action-btn edit" data-id="${sc.maSuatChieu}">Sửa</button>
        <button class="action-btn delete" data-id="${sc.maSuatChieu}">Xóa</button>
      </td>`;
    tbody.appendChild(tr);
  });
  attachAdminEvents();
}

function attachAdminEvents() {
  document.querySelectorAll('.edit').forEach(btn => {
    btn.onclick = async () => {
      const id = btn.dataset.id;
      const sc = await fetch(`${API_BASE}/${id}`).then(r => r.json());
      document.getElementById('sc-id').value = sc.maSuatChieu;
      document.getElementById('movie').value = sc.movie;
      document.getElementById('time').value = sc.time.slice(0,16);
      document.getElementById('room').value = sc.room;
      document.getElementById('form-title').textContent = 'Sửa Suất Chiếu';
      document.getElementById('submit-btn').textContent = 'Cập nhật';
    };
  });
  document.querySelectorAll('.delete').forEach(btn => {
    btn.onclick = async () => {
      if (confirm('Bạn chắc chắn muốn xóa?')) {
        await fetch(`${API_BASE}/${btn.dataset.id}`, { method: 'DELETE' });
        await updateAll();
      }
    };
  });
}

async function handleAdminForm(e) {
  e.preventDefault();
  const id = document.getElementById('sc-id').value;
  const payload = {
    movie: document.getElementById('movie').value,
    time: document.getElementById('time').value,
    room: document.getElementById('room').value
  };
  if (id) {
    // update
    await fetch(`${API_BASE}/${id}`, {
      method: 'PUT',
      headers: {'Content-Type':'application/json'},
      body: JSON.stringify(payload)
    });
  } else {
    // create
    await fetch(API_BASE, {
      method: 'POST',
      headers: {'Content-Type':'application/json'},
      body: JSON.stringify(payload)
    });
  }
  resetAdminForm();
  await updateAll();
}

function resetAdminForm() {
  document.getElementById('sc-form').reset();
  document.getElementById('sc-id').value = '';
  document.getElementById('form-title').textContent = 'Thêm Suất Chiếu';
  document.getElementById('submit-btn').textContent = 'Lưu';
}

// ------ CUSTOMER PAGE ------

async function renderCustomerList() {
  const list = await fetchAll();
  const ul = document.getElementById('sc-list');
  ul.innerHTML = '';
  list.forEach(sc => {
    const li = document.createElement('li');
    li.dataset.id = sc.maSuatChieu;
    li.innerHTML = `
      <span>${sc.movie} — ${formatDateTime(sc.time)} (Phòng ${sc.room})</span>
      <button class="choose">Chọn</button>
    `;
    ul.appendChild(li);
  });
  document.querySelectorAll('#sc-list .choose').forEach(btn => {
    btn.onclick = () => {
      const id = btn.parentElement.dataset.id;
      // chỉ đóng vai trò chọn => chuyển trang chọn phòng (chưa implement)
      alert('Bạn đã chọn suất chiếu ID=' + id);
    };
  });
}

// ------ SYNC & INIT ------

async function updateAll() {
  if (document.body.id === 'admin-page') {
    await renderAdminTable();
  }
  if (document.body.id === 'customer-page') {
    await renderCustomerList();
  }
}

// Khởi tạo
window.addEventListener('DOMContentLoaded', () => {
  if (document.body.id === 'admin-page') {
    document.getElementById('sc-form').addEventListener('submit', handleAdminForm);
    document.getElementById('cancel-btn').addEventListener('click', resetAdminForm);
  }
  updateAll();
});
