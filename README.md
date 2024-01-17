# FIZIKO

This project was my team and I's final project for the 4th and final java programming class in Cégep Limoilou's Computer Science and Mathematics program.

It is a simple dart game where the player has to shoot a dart on a dartboard with progressingly more difficult levels. Obstacles are added in later levels and gravity changes. We used Bézier curves to trace the trajectory of the projectile instead of using kinematic equations to calculate it in real time, which was a mistake in hindsight.

The project was adapted to be built with maven in 2024, the executable jar is located in the latest github release of the repo.

The project only really works on a 1920x1080 screen because the image sizes were hardcoded and not dynamically resized. It is possible to run it with other resolutions but a few elements will probably be outside the screen.
