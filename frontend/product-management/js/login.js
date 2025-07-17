import { sendForm } from "../../common.js";

const button = document.getElementById("submit-button");

button.addEventListener("click", async () => {
    const form = document.getElementById("admin-form");
    sendForm(form, "/token/admin", "top.html");
});