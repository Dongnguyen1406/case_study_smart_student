document.addEventListener('DOMContentLoaded', () => {
  const rows = document.querySelectorAll('tbody tr');

  rows.forEach(row => {
    const inputQuiz = row.cells[2].querySelector('input');
    const inputPractice = row.cells[3].querySelector('input');
    const cellAverage = row.cells[4];
    const cellResult = row.cells[5];
    const checkboxAttendance = row.cells[6].querySelector('input');
    const cellStatus = row.cells[7];
    const selectEvaluation = row.cells[8].querySelector('select');

    function validateScore(input) {
      let value = parseFloat(input.value);
      if (isNaN(value)) value = 0;
      if (value > 10) value = 10;
      if (value < 0) value = 0;
      input.value = value.toFixed(1);
      return value;
    }

    function updateRow() {
      const quizScore = validateScore(inputQuiz);
      const practiceScore = validateScore(inputPractice);
      const average = ((quizScore + practiceScore) / 2).toFixed(1);
      cellAverage.textContent = average;

      const isAttend = checkboxAttendance.checked;
      const isPassed = average >= 5.0;

      // Đạt yêu cầu = điểm TB ≥ 5.0 và có tham gia đầy đủ
      if (isPassed && isAttend) {
        cellResult.innerHTML = '<span class="badge bg-success">Đạt</span>';
        cellStatus.innerHTML = '<span class="badge bg-success">Hoàn thành</span>';
      } else {
        cellResult.innerHTML = '<span class="badge bg-danger">Chưa đạt</span>';
        cellStatus.innerHTML = '<span class="badge bg-danger">Chưa hoàn thành</span>';
      }

      // Gợi ý đánh giá
      if (average >= 9) {
        selectEvaluation.value = "Hoàn thành xuất sắc";
      } else if (average >= 7.5) {
        selectEvaluation.value = "Hoàn thành tốt";
      } else if (average >= 5.0) {
        selectEvaluation.value = "Cần cố gắng thêm";
      } else {
        selectEvaluation.value = "Cần hỗ trợ";
      }
    }

    // Gọi khi bắt đầu
    updateRow();

    // Cập nhật khi thay đổi điểm hoặc checkbox
    inputQuiz.addEventListener('input', updateRow);
    inputPractice.addEventListener('input', updateRow);
    checkboxAttendance.addEventListener('change', updateRow);
  });
});
