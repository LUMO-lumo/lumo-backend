import http from 'k6/http';
import { check, sleep } from 'k6';

const USER_COUNT = __ENV.USER_COUNT || 10000;
const BURST_COUNT = __ENV.BURST_COUNT || 3;

// 테스트 파일 입니다.

export const options = {
    vus: USER_COUNT,
    duration: '20s',
};

export default function () {
    const vuId = __VU;
    const email = `user${vuId}@email.com`;

    // 1. URL에 직접 쿼리 파라미터를 붙입니다.
    // http.post 시 Body는 비워둡니다 (null).
    const url = `http://3.34.247.56:8081/api/member/request-code?email=${email}`;

    const params = {
        headers: { 'Content-Type': 'application/json' },
    };

    for (let i = 0; i < BURST_COUNT; i++) {
        const res = http.post(url, null, params); // Body는 null

        // 2. 성공 여부 체크 (200 OK인지 확인)
        check(res, {
            'is status 200': (r) => r.status === 200,
        });
    }

    sleep(1);
}