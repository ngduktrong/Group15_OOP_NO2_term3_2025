// static/js/suatchieu.js
// Logic tương tự phim.js: sử dụng FormData để POST, xóa qua GET, xử lý cả admin list và form

document.addEventListener('DOMContentLoaded', function() {
  const page = document.body.id;
  if (page === 'admin-list-page') bindDeleteLinks();
  if (page === 'admin-form-page') bindForm();
});

function bindDeleteLinks() {
  document.querySelectorAll('.delete-link').forEach(link => {
    link.addEventListener('click', function(e) {
      e.preventDefault();
      if (!confirm('Bạn có chắc muốn xóa suất chiếu này?')) return;
      const url = link.getAttribute('data-delete-url');
      fetch(url, { method: 'GET' })
        .then(resp => {
          if (!resp.ok) throw new Error('Delete failed');
          window.location.href = '/suatchieu/admin';
        })
        .catch(err => {
          console.error(err);
          alert('Xóa thất bại');
        });
    });
  });
}

function bindForm() {
  const form = document.getElementById('sc-form');
  const cancelBtn = document.getElementById('cancel-btn');
  cancelBtn.addEventListener('click', function() {
    window.location.href = '/suatchieu/admin';
  });

  form.addEventListener('submit', function(e) {
    e.preventDefault();
    const formData = new FormData(form);
    const id = formData.get('maSuatChieu');
    let url = '/suatchieu/api';
    if (id) {
      url += '/' + id;
      formData.delete('maSuatChieu');
    }

    fetch(url, {
      method: 'POST',
      body: formData
    })
    .then(resp => {
      if (!resp.ok) throw new Error('Submit failed');
      window.location.href = '/suatchieu/admin';
    })
    .catch(err => {
      console.error(err);
      alert('Lưu dữ liệu thất bại');
    });
  });
}
