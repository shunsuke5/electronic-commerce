import { auth } from "../../common.js";

const button = document.getElementById("register");

button.addEventListener("click", async () => {
    auth("/admin/auth", "product-register.html");
});