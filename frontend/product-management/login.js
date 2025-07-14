import { BASE_URL } from "./constant.js";

const button = document.getElementById("submit-button");

button.addEventListener("click", async () => {
    const form = document.getElementById("admin-form");
    const formData = new FormData(form);

    try {
        const response = await fetch(BASE_URL + "/token/admin", {
            method: "POST",
            body: formData,
        });

        if (response.ok) {
            window.location.href = "top.html";
        } else if (response.status === 401) {
            alert("認証失敗");
        } else {
            alert("エラー:" + response.statusText);
        }
    } catch (error) {
        console.error("通信エラー:", error);
    }
});