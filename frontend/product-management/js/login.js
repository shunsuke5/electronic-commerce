import { login } from "../../common.js";

const button = document.getElementById("submit-button");

button.addEventListener("click", async () => {
    const form = document.getElementById("admin-form");
    login(form, "/token/admin", "../html/top.html");
});