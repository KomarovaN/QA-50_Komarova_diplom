### Итоги автоматизированного тестирования
#### Краткое описание
Автоматизированы запланированные (_Plan.md_) позитивные и негативные сценарии покупки тура.

Разработаны тесты:
* UI-тесты - классы _TravelTest, TravelCreditTest_;
* API-тесты - класс _TravelApiTest_;
* по результатам прогона тестов получены репорты Allure.
#### Общее количество тест-кейсов: 34.
* _TravelTest_ - 16,
* _TravelCreditTest - 16,
* _TravelApiTest_ - 2.
#### Процент успешных и не успешных тест-кейсов: 35% успешных, 65% неуспешных.
* _TravelTest_ - 37% успешных, 63% неуспешных,
* _TravelCreditTest_ - 37% успешных, 63% неуспешных,,
* _TravelApiTest_ - 100% неуспешных.

Репорты Allure:

  ![img.png](images/img.png)
  ![img2.png](images/img2.png)
  ![img1.png](images/img1.png)
 

#### Bag-репорты: открыты 5 issues.
* [В поле "Владелец" можно ввести цифры, любые знаки, буквы не латинского алфавита в неограниченном количестве #1](https://github.com/KomarovaN/QA50-diplom/issues/1),
* [В поле "Месяц" можно ввести значение больше 12 #2](https://github.com/KomarovaN/QA50-diplom/issues/2),
* [В ответ на запрос POST симулятору сервисов банка, отправленному с валидными данными, возвращается код ошибки сервера 500 #3](https://github.com/KomarovaN/QA50-diplom/issues/3),
* [В базе данных нет записей о статусе оплаты после получения ответа от симулятора сервисов банка #4](https://github.com/KomarovaN/QA50-diplom/issues/4),
* [Если поля "Месяц" и "Номер карты" пустые, то выводится сообщение "Неверный формат" вместо сообщения "Поле обязательно для заполнения" #5](https://github.com/KomarovaN/QA50-diplom/issues/5).

#### Общие рекомендации.
Тесты приложения (_классы TravelTest, TravelCreditTest) с проверкой отображения сообщений банка о результатах выпонения операции простой оплаты:
* shouldPayCardValidApproved(),
* shouldNotPayCardValidApproved();
* shouldPayCardValidDeclined(),
* shouldNotPayCardValidDeclined().
* shouldCreditCardValidApproved(),
* shouldNotCreditCardValidApproved(),
* shouldCreditCardValidDeclined(),
* shouldNotCreditCardValidDeclined();

необходимо повторить после устранения ошибки запроса к симулятору сервисов банка с кодом 500,
то есть когда симулятор сервисов банка вернет код выполнения запроса 200,
и API-тесты (_class TravelApiTest_) пройдут успешно.

![img_6.png](images/img_6.png)
![img_7.png](images/img_7.png)


