# xkcd-mobile-app
## Обзор приложения
Приложение разработано для просмотра комиксов xkcd (https://xkcd.com/) в своём смартфоне. Для получения комиксов, приложения использует общедоступное API.

### Тёмная тема:
<p align="center" width="100%">
    <img width="24%" src="https://github.com/PaulMak-del/xkcd-mobile-app/assets/72689234/4b394c11-4cee-44f7-a43e-fbcab3f7056b"> 
    <img width="24%" src="https://github.com/PaulMak-del/xkcd-mobile-app/assets/72689234/f62201bc-c6d7-4373-bbc7-3097f441a94c"> 
    <img width="24%" src="https://github.com/PaulMak-del/xkcd-mobile-app/assets/72689234/ec96147b-607f-406e-ad5c-f83ecf484450"> 
    <img width="24%" src="https://github.com/PaulMak-del/xkcd-mobile-app/assets/72689234/735b7f01-d55e-4529-a4e9-e18f6e869d64"> 
</p>

### Светлая тема:
<p align="center" width="100%">
    <img width="24%" src="https://github.com/PaulMak-del/xkcd-mobile-app/assets/72689234/92e6776f-3c2f-49f1-8267-66539ddfa669"> 
    <img width="24%" src="https://github.com/PaulMak-del/xkcd-mobile-app/assets/72689234/23845f52-6af3-4c67-9df2-73660d2fd640"> 
    <img width="24%" src="https://github.com/PaulMak-del/xkcd-mobile-app/assets/72689234/4d7678c9-6667-4da3-ab95-0fc958439c4c"> 
    <img width="24%" src="https://github.com/PaulMak-del/xkcd-mobile-app/assets/72689234/61114cae-9159-4aa3-b988-d6cd0a002cc7"> 
</p>

### Основные функции:
1. Подгрузка комиксов с сайта в приложение;
2. Возможность добавить комикс в избранное];
3. [Возможность поделиться комиксом с другими приложениями](https://github.com/PaulMak-del/xkcd-mobile-app/assets/72689234/5b076802-31a5-47a0-a648-af5c36a18543 "Пример");
4. Возможность изменить тему приложения];
5. Реализована навигация по комиксам (первый/предыдущий/случайный/следующий/последний комикс, поиск по номеру комикса);
6. [Обработаны возможные ошибки при работе приложения (нет доступа в интернет, поиск по несуществующему комиксу)](https://github.com/PaulMak-del/xkcd-mobile-app/assets/72689234/d8039dec-aedb-4b74-ba85-2e485e1dcbd0 "Пример");

## Используемые библиотеки и технологии
1. Приложение разработано на принципах чистой архитектуры с многомодульностью;
2. Room (Для хранения понравившихся комиксов);
3. DataStore (Для хранения данных типа "ключ-значение");
4. Retrofit2 и okHttp для обращения к удалённому API;
5. Hilt (Внедрение зависимостей);
6. MVVM (Арзитектура приложения);
7. Coil (Для загрузки изображений из сети);
8. Jetpack Compose (UI приложения);

## Пример работы приложения

<img width="24%" src="https://github.com/PaulMak-del/xkcd-mobile-app/assets/72689234/e7c74348-24ab-46b5-9a55-1c34b9b19bd9"> 
