# CST 338 Course Resources

Shared files for CST 338 Software Design at CSU Monterey Bay: starters, templates, build
files and student guides, collected in one place in the course organization.

These previously lived as **gists on an individual account**. They now live here because
course material should belong to the organization rather than to a person: access can be
granted through the `instructors` and `tas` teams, and nothing depends on one account.

> **The original gists are still live and nothing in Canvas has been changed.** Existing
> links keep working. The table below maps every old gist to its new path, so links can be
> migrated as each assignment comes around rather than all at once.

---

## Where everything went

| Old gist | Now at | What it is |
|---|---|---|
| [`8426c6a2`](https://gist.github.com/dclinkenbeard/8426c6a2a0299b140c112b81efd18385) | [`project-02/README_TEMPLATE.md`](project-02/README_TEMPLATE.md) | Project 2 repo README template |
| [`e5f70b35`](https://gist.github.com/dclinkenbeard/e5f70b3504c509617e78571674866736) | [`project-02/archive/PROMPT_v2_2026-07.md`](project-02/archive/PROMPT_v2_2026-07.md) | Project 2 prompt **V2**, superseded |
| [`81ed6ad5`](https://gist.github.com/dclinkenbeard/81ed6ad555cb780a3d361f95f50179f6) | [`javafx/build.gradle`](javafx/build.gradle) | JavaFX Gradle build, JavaFX 21 |
| [`2de98ae4`](https://gist.github.com/dclinkenbeard/2de98ae4535facf2577b3c3ed84b4427) | [`javafx/build.gradle.javafx22`](javafx/build.gradle.javafx22) | The same build pinned to JavaFX 22 |
| [`a3f2ea70`](https://gist.github.com/dclinkenbeard/a3f2ea7085b572fa5c0ac220307cf498) | [`javafx/WINDOWS_ARM64_SETUP.md`](javafx/WINDOWS_ARM64_SETUP.md) | Running JavaFX on Windows on ARM |
| [`68b9ec62`](https://gist.github.com/dclinkenbeard/68b9ec62cb9d19a70d65aa72c8222293) | [`gitignore/cst338.gitignore`](gitignore/cst338.gitignore) | The course `.gitignore` |
| [`c8d74ac4`](https://gist.github.com/dclinkenbeard/c8d74ac423d189044e51f8baca3c3219) | [`labs/lab00/README.md`](labs/lab00/README.md) | Lab 00 |
| [`36fc15d6`](https://gist.github.com/dclinkenbeard/36fc15d693b9e0839cd992639a3750d4) | [`starters/Polygon.java`](starters/Polygon.java) | Polygon starter |
| [`c1ff27c0`](https://gist.github.com/dclinkenbeard/c1ff27c0245cd864c29028d382919cad) | [`lecture-examples/Product.java`](lecture-examples/Product.java) | Lecture 14 example |
| [`cfd9186e`](https://gist.github.com/dclinkenbeard/cfd9186e2bb06c5e8e2d1d13ed6e4ec0) | [`gymlog/README.md`](gymlog/README.md) | GymLog readme |
| [`cd2deddf`](https://gist.github.com/dclinkenbeard/cd2deddff77225bbffe285cb8e2eb473) | [`gymlog/VIDEO_DESCRIPTIONS.md`](gymlog/VIDEO_DESCRIPTIONS.md) | GymLog walkthrough videos |
| [`698cf34f`](https://gist.github.com/dclinkenbeard/698cf34f3e62909946996ff4f1ddaff5) | [`gymlog/SUPPORTING_TEXT.md`](gymlog/SUPPORTING_TEXT.md) | GymLog supporting text |
| [`66617c41`](https://gist.github.com/dclinkenbeard/66617c41e0c0991b555216ad4bdfea76) | [`library-project/Code.java`](library-project/Code.java) | Library project `Code` enum |
| [`59ec59c2`](https://gist.github.com/dclinkenbeard/59ec59c29a8e6b7a6d124143e4bfa9db) | [`library-project/driver/`](library-project/driver/) | Library driver, `Main`, `Util`, 2 CSVs |
| [`004ac7fa`](https://gist.github.com/dclinkenbeard/004ac7fabc6e240133fe7ac2ad513d8e) | [`android-archive/strings.xml`](android-archive/strings.xml) | Android era, kept for reference |
| [`f695ea94`](https://gist.github.com/dclinkenbeard/f695ea94956993e122cce1bfc33fe510) | [`android-archive/activity_main.xml`](android-archive/activity_main.xml) | Android era, kept for reference |
| [`49d08c5f`](https://gist.github.com/dclinkenbeard/49d08c5fda66f72aaf54d76cf910e192) | [`student-guides/add-csumb-email-to-github.md`](student-guides/add-csumb-email-to-github.md) | Adding a CSUMB email to an existing GitHub account |

---

## Deliberately not copied here

**This repository is public, so three kinds of thing were left where they were.**

### Reference test suites

`ShelfTest.java`, `LibraryTest.java` and both Markov test suites are the tests an assignment
is graded against. They are currently **secret gists**, meaning readable only by someone who
has the link. Moving them into a public, search-indexed repository is a different thing from
sharing a link with one cohort, and it would hand future students the grader. They stay as
gists.

If they should be shared, a **private** repository in this organization is the right home;
the `tas` team already exists to be granted it.

### Grading and plagiarism tooling

`moss.py` / `Moss_prep.py`, which organize Canvas submissions for similarity checking. This
is instructor tooling, and publishing it tells students exactly what the detection pipeline
does. It belongs in a private repository, not here.

### Other courses

CST 438 and CST 319 material was left alone; it belongs in those courses' own homes rather
than in the CST 338 organization.

---

## Related repositories

| Repo | What |
|---|---|
| `CST-338/p2-javafx-prompt` | Project 2 prompt **V3**, current, and the ten project options |
| `CST-338/p2-javafx-starter` | Project 2 starter: JavaFX, H2, SceneFactory, dev-login bypass |
| `CST-338/ftoc-converter` | Fahrenheit to Celsius JavaFX lab template |
| `CST-338/javafx-todo` | Todo app, the God Method through Factory sequence |
| `CST-338/learninglog` | Weekly learning log |

## A note on the two build files

`build.gradle` and `build.gradle.javafx22` differ by **one line**, the JavaFX version (21
against 22). Both are kept because both were handed out, and a student following an older
Canvas link should find the file it promised. New work should use the build in
`p2-javafx-starter`, which detects whether the JDK already bundles JavaFX instead of pinning
a version.
