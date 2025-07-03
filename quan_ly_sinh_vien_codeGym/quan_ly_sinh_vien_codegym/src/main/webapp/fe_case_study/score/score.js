document.addEventListener("DOMContentLoaded", () => {
  const rows = document.querySelectorAll("tbody tr");

  rows.forEach((row) => {
    const inputQuiz = row.cells[2].querySelector("input");
    const inputPractice = row.cells[3].querySelector("input");
    const inputAverage = row.cells[4].querySelector("input");
    const checkboxStatus = row.cells[5].querySelector("input");
    const statusTextNode = row.cells[5].childNodes[1]; // Text "Hoàn thành"

    function validateScore(input) {
      let value = parseFloat(input.value);
      if (isNaN(value)) value = 0;
      if (value > 10) value = 10;
      if (value < 0) value = 0;
      input.value = value.toFixed(1);
      return value;
    }

    function updateRow() {
      const quiz = validateScore(inputQuiz);
      const practice = validateScore(inputPractice);
      const average = ((quiz + practice) / 2).toFixed(1);
      inputAverage.value = average;

      if (average >= 5) {
        checkboxStatus.checked = true;
        statusTextNode.textContent = " Hoàn thành";
      } else {
        checkboxStatus.checked = false;
        statusTextNode.textContent = " Chưa hoàn thành";
      }
    }

    // Gọi ban đầu và khi thay đổi
    updateRow();
    inputQuiz.addEventListener("input", updateRow);
    inputPractice.addEventListener("input", updateRow);
  });
});
