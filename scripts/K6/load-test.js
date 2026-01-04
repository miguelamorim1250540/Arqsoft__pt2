import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  stages: [
    { duration: '30s', target: 20 },
    { duration: '1m', target: 50 },
    { duration: '30s', target: 0 },
  ],
};

export default function () {
  const url = `${__ENV.BASE_URL}/api/lendings`;

    const id = Math.floor(Math.random() * 100000);

    const payload = JSON.stringify({
      bookId: id.toString(),
      readerId: id.toString(),
      isbn: id.toString(),
      readerNumber: id.toString()
    });

  const params = {
    headers: {
      'Content-Type': 'application/json',
      'Accept': '*/*',
    },
  };

  const res = http.post(url, payload, params);

  check(res, {
    'status is 200 or 201': (r) => r.status === 200 || r.status === 201,
  });

  sleep(1);
}