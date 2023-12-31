# Тестовое задание на Java.

## Содержание

- [Условие](#Условие)
- [Логика приложения](#логика-приложения)
- [Документация](#Документация)
- [Запуск](#Запуск)
- [Связаться со мной]()



## Условие 
Условие данной задачи доступно по данной [ссылке](https://docs.google.com/document/d/1lfpe1JDCuGMQ3cFyn5oNk2PqRO94z6IqCq6yoTaUsYo/edit).

## Описание приложения

Все классы, которые необходимо было реализовать в условии находятся в [src/main/java/ru/testtask/models](./src/main/java/ru/testtask/models).

В дополнении к простым классам сущностей были добавлены классы для запуска боя, добавления и удаления новых сущностей и др. в командной строке.

Список всех доступных команд:

- show_players: показать список всех объектов Player
- exit: выйти из приложения
- help: справка о всех командах
- add_monster: добавление нового объекта Monster
- show_monsters: показать список всех объектов Monster
- start: start Запустить бой всех объектов Player против всех объектов Monster
- remove_player: удалить объект Player из списка
- add_player: добавление нового объекта Player
- remove_monster: удалить объект Monster из списка

Пользователь может выполнить любую из команд. В бою пользователь играет за объектов Player, его цель убить все объекты Monster.

Логика боя следующая:

Данная игра представляет собой пошаговый бой, сначала один из объектов
Player, который выбирается случайным образом, атакует объект Monster,
который также выбирается случайным образом, затем пользователю,
играющему за объекты Player, предоставляется возможность либо лечить
Player, который только что атаковал, либо не лечить.
Затем выбирается случайный монстр и атакует случайного игрока.
Любое существо живет до тех пор, пока его здоровье больше 0.
Один ход длится до тех пор, пока все игроки или монстры не умрут.
После этого начинается следующий ход, и все начинается сначала.
Игра длится до тех пор, пока не будут удалены все монстры или игроки из списка доступных.


## Документация
Документация по всем классам оформлена в javadoc. 

Путь к документации: [build/docs/javadoc/index.html](./build/docs/javadoc/index.html)

## Запуск

Скачать openjdk version "17.0.5", после чего склонировать репозиторий, перейти в папку TestTask и запустить java -jar.
```bash
sudo apt install openjdk-17-jdk 
git clone https://github.com/oAnthocyane/GraphicApp.git
cd GraphicApp
java -jar ./build/libs/TestTask-1.0.jar
```

Для пересборки проекта использовать Gradle 8 версии и команду:
```bash
gradle clean build
```

## Связаться со мной

Telegram: https://t.me/Syrnick
