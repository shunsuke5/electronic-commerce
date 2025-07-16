import { BASE_URL } from "./constant.js";

export async function signup(form, path, destPage) {
    const formData = new FormData(form);

    try {
        const response = await fetch(BASE_URL + path, {
            method: "POST",
            body: formData,
        });

        if (response.ok) {
            window.location.href = destPage;
        } else if (response.status === 401) {
            alert("認証失敗");
        } else {
            alert("エラー:" + response.statusText);
        }
    } catch (error) {
        console.error("通信エラー:", error);
    }
}

export async function login(form, path, destPage) {
    const formData = new FormData(form);

    try {
        const response = await fetch(BASE_URL + path, {
            method: "POST",
            body: formData,
            credentials: "include"
        });

        if (response.ok) {
            window.location.href = destPage;
        } else if (response.status === 401) {
            alert("認証失敗");
        } else {
            alert("エラー:" + response.statusText);
        }
    } catch (error) {
        console.error("通信エラー:", error);
    }
}

export async function isExpireToken(path) {
    const response = await fetch(BASE_URL + path, {
        method: "GET",
        credentials: "include"
    });

    return !response.ok;
}

export async function auth(path, destPage) {
    try {
        const response = await fetch(BASE_URL + path, {
            method: "GET",
            credentials: "include"
        });

        if (response.ok) {
            window.location.href = destPage;
        } else if (response.status === 403) {
            window.location.href = "login.html";
        } else {
            alert("エラー:" + response.statusText);
        }
    } catch (error) {
        console.error("通信エラー:", error);
    }
}