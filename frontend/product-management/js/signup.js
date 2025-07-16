import { signup } from "../../common.js";

const button = document.getElementById("submit-button");

button.addEventListener("click", async () => {
    const form = document.getElementById("admin-form");
    signup(form, "/admin/create", "../html/login.html");
});