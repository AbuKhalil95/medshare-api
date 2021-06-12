const oFile = document.getElementById("imageUpload").files[0];

// TODO: Prevent image upload for profile and item that is more than 1 MB
// 1 MiB for bytes.
if (oFile.size > 1048576) {
  alert("File size must under 1MiB!");
  return;
} else {
}