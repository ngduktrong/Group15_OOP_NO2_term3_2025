// static/js/phim.js
document.addEventListener('DOMContentLoaded', function() {
  // XỬ LÝ CHO ADMIN: XÓA PHIM
  document.querySelectorAll('.delete-link').forEach(link => {
    link.addEventListener('click', function(e) {
      e.preventDefault();
      if (!confirm('Bạn có chắc muốn xóa phim này?')) return;
      // Call fetch lên /phim/delete/{id}
      fetch(link.getAttribute('data-delete-url'), { method: 'GET' })
        .then(() => {
          // Sau khi xóa xong, chuyển về /phim/list tương đối
          window.location.href = 'list';
        })
        .catch(err => {
          console.error('Lỗi khi xóa phim:', err);
          alert('Xóa phim thất bại.');
        });
    });
  });

  // XỬ LÝ CHO CUSTOMER: ĐẶT VÉ (giữ nguyên)
  document.querySelectorAll('.book-ticket').forEach(btn => {
    btn.addEventListener('click', function(e) {
      e.preventDefault();
      const phimId = this.getAttribute('data-id');
      window.location.href = `/customer/booking/${phimId}`;
    });
  });
});
