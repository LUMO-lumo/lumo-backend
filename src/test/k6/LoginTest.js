import http from 'k6/http';
import { check } from 'k6';

export const options = {
    vus: 1,           // 가상 사용자 1명
    iterations: 1,    // 총 실행 횟수 1회
};

export default function () {
    const url = 'http://13.124.31.129/api/member/login';

    const payload = JSON.stringify({
        email: 'jijysun@naver.com',
        password: 'yji1923!',
    });

    const params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    const res = http.post(url, payload, params);

    // 성공 여부 확인
    check(res, {
        'is status 200': (r) => r.status === 200,
    });
}