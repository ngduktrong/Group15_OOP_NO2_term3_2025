// static/js/phim.js

document.addEventListener('DOMContentLoaded', function() {
  // --- XỬ LÝ CHO ADMIN: XÓA PHIM ---
  document.querySelectorAll('.delete-link').forEach(link => {
    link.addEventListener('click', function(e) {
      e.preventDefault();
      if (!confirm('Bạn có chắc muốn xóa phim này?')) return;
      // Redirect tới URL xóa lấy từ attribute data-url
      window.location.href = this.getAttribute('data-url');
    });
  });

  // --- XỬ LÝ CHO CUSTOMER: ĐẶT VÉ ---
  document.querySelectorAll('.book-ticket').forEach(btn => {
    btn.addEventListener('click', function(e) {
      e.preventDefault();
      const phimId = this.getAttribute('data-id');
      // Redirect sang trang đặt vé (bạn thay thế URL tuỳ flow)
      window.location.href = `/customer/booking/${phimId}`;
    });
  });
});
