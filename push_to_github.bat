@echo off
chcp 65001 >nul
cd /d "C:\Users\H2\Documents\DataTFT\app\src\main"

echo [1/4] Khoi tao Git...
git init

echo [2/4] Them cac file...
git add .

echo [3/4] Commit...
git commit -m "Upload app src main"

echo [4/4] Day len GitHub...
git branch -M main
git remote remove origin 2>nul
git remote add origin https://github.com/no9pcorp-dot/DataTFT.git
git push -u origin main --force

echo.
echo ==========================================
echo Hoan tat! Nhan phim bat ky de thoat.
pause
