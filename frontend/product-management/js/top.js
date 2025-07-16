import { auth } from "../../common.js";

const button = document.getElementById("product-management");

button.addEventListener("click", async () => {
    auth("/admin/auth", "product-index.html");
});