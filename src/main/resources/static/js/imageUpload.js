// TODO: Prevent image upload for profile and item that is more than 1 MB
// 1 MiB for bytes.
function validateSize(input) {
    const fileSize = input.files[0].size / 1024 / 1024;
    if (oFile.size > 2) {
      alert("File size must under 2MiB!");
      input.value = '';
      return;
    } else {
        
    }
}