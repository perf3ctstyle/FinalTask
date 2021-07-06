function limitNumberInput(inputId) {
    let currentValueLength = document.getElementById(inputId).value.length;
    let maxLength = document.getElementById(inputId).maxLength;
    if (currentValueLength > maxLength) {
        document.getElementById(inputId).value = document.getElementById(inputId).value.slice(0, maxLength);
    }
}