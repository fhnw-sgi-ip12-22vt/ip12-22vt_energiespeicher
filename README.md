# Clash of Energiespeicher

FHNW Computer Science Student Project Semester 1&2.
Strebe eine effiziente Stromversorgung deiner Stadt an, um einen neuen Highscore zu erreichen und deinen Gegner zu übertrumpfen. Ineffiziente Energieverwaltung kann zu einem Game Over führen!

## Systeminformationen

RaspberryPi: Model B

Betriebssystem: OS based on Debian 11

Programmiersprache: Java 17

## Installation

Um das Projekt nach Änderungen auf dem Raspberry Pi zu installieren ist folgender Vorgang nötig:

1. Build Project mit Maven: `mvn package -Dmaven.test.skip `

2. Kopieren des JAR-Files auf den Raspberry Pi über CMD: `scp target/clashofenergiespeicher-0.0.1-jar-with-dependencies.jar pi@[ip-adress]:/home/pi/Desktop/game`

Danach sollte das Projekt per Autostart wieder wie gewohnt starten.

## Team Energiespeicher

Raphael Kumbartzki raphael.kumbartzki@students.fhnw.ch

Yuri Aebi yuri.aebi@students.fhnw.ch

Andrin Sibold andrin.sibold@students.fhnw.ch

Francine Schwarb francine.schwarb@students.fhnw.ch

Livia Stöcklin livia.stoecklin@students.fhnw.ch

Damjan Stojanovic damjan.stojanovic@students.fhnw.ch
