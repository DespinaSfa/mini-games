GIT/GITLAB: Fragen und Übungen



Bianca Knülle, 43373/ Despoina Sfantou, 43368/ Julia Fellmeth, 43391/ Kethella Oliveira, 43385
….....................................................................................................
Vorname, Name, Matrikelnummer


1. Was genau sind die Gründe um Gitlab zu verwenden?

-Group work
-Project is saved in an extern server
-Communication
-Simultaneously work
-Organisation




2. Welche Daten gehören (nicht) ins Repo?
- .java Files
- .xml Files
- .Jason Files
- Bilddateien für Gameprojekte
- Musikdateien für Projekte
- .project Files (Endung je nach Tool)	    	x
- UML Modelle
- Zeichnungen					            	x
- Notizen					                	x
- Dokumentation
- Configurationsfiles
- Kapitel eines Buches				            x
- Eine Bachelorarbeit				        	x
- Passwörter für Cloud-Services			        x
- Passwörter für lokale Services (Self-hosted)	x
- logfiles						                x
- Messdaten vom Profiling				        x







3. Was soll der „Mist“ mit den Stages (dass add/commit nur lokal wirken)?

 -Merge conflict
 -Local storing —> not every member of the team has the same version at the same time


4. Würden Sie in einer Firma Gitlab selber hosten oder GIT als Service im Netz? Begründung.

-Gitlab is safer to use because it’s more secure to host locally




5. Verwenden Sie Branches im Projekt oder arbeiten alle Teammitglieder auf dem Master Branch? Zeigen Sie Vor- und Nachteile der Verfahren

-Master
Pros:
Code is always up to date

Cons:
Merge conflicts
Peril of deleting the main code

-Different Branches
Pros:
Main code is secured
Less merge conflicts
Easier to keep track of code changes

Cons:
Merge to the master branch is necessary




6. Wie veröffentlichen Sie Ihre Änderungen auf dem globalen Repository? Wie oft checken Sie Ihre Änderungen im globalen Repo ein? Was ist besser: Nach jeder Änderung, nach einigen Änderungen, wenn Sie ein zusammenhängendes Stück fertig haben, wenn Sie eine Änderung machen die viele Kollegen betrifft, einmal am Tag, einmal die Woche. Wieso?

1. With commit and push are the changes to the global repository completed.
2. One checks the changes of the global repo before starting to make new changes or alterations.
3. It is not obligatory to pull the project after unnecessary changes




7. Eine Version eines Files im Repo sieht so aus:
„There are two versions of GitLab: Community Edition (CE) and Enterprise Edition (EE)“
Ihre Version die Sie hochladen wollen hat an dieser Stelle:
„There are two versions of GitLab: CE and EE.“
Wie löst Gitlab diesen Konflikt?
 -There are two versions of a project when a pull is performed, namely CE and EE. The data that’s same in both versions dominates and thus is kept.
 -The user has to decide what happens with the rest of the data.


8. Hausaufgabe für jedes Team: Es muss eine chain-story (Kettengeschichte) erzählt werden bei der ALLE Teammitglieder jeweils immer einen Satz erfinden und dann einchecken. Ich will von ALLEN Teammitgliedern Sätze im Repo finden! Danach sagen Sie uns wie es ging. Haben Sie über externe Kanäle kommuniziert (Slack?). Anderweitig abgesprochen?
--> AufDemWegZumMond.md

