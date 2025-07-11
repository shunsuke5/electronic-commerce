import { BASE_URL } from "./constant";

const button = document.getElementById("submit-button");

button.addEventListener("click", async () => {
    const form = document.getElementById("admin-form");
    const formData = new FormData(form);

    try {
        const response = await fetch(BASE_URL + "/create/admin", {
            method: "POST",
            body: formData,
        });

        if (response.ok) {
            window.location.href = "login.html";
        } else if (response.status === 401) {
            alert("認証失敗");
        } else {
            alert("エラー:" + response.statusText);
        }
    } catch (error) {
        console.error("通信エラー:", error);
    }
});