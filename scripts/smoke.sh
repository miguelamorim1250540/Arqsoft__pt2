#!/bin/bash
set -e

BASE_URL=$1

echo "🔎 Smoke test em $BASE_URL"

curl -f "$BASE_URL/actuator/health"

echo "✅ Smoke tests OK"