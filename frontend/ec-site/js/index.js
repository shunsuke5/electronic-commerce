import { isValidToken } from "../../common.js";

const isTokenValid = await isValidToken("/customer/auth");
if (isTokenValid) {
    console.log("商品表示");
    // 商品一覧を表示する
}