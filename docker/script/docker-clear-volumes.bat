@echo off
FOR /f "tokens=*" %%i IN ('docker volume ls -q') DO docker volume rm %%i
