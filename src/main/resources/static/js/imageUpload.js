
function validateSize(input) {
    const fileSize = input.files[0].size / 1024 / 1024;
    console.log(fileSize);

    if (fileSize > 2) {
      alert("File size must under 2MiB!");
      input.value = '';
      return;
    } else {

    }
}