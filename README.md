# A simple proof-of-concept project
  
  This is written to experiment with
 - [clojure.tools.deps](https://github.com/clojure/tools.deps.alpha)
 - [Integrant](https://github.com/weavejester/integrant)
 - [The Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
 - [Reloaded](https://github.com/stuartsierra/reloaded) 

#### Starting a REPL with development configuration
##### Clojure CLI REPL
```bash
clj -Adev
```
##### nREPL
```bash
clj -Adev:nrepl
```

and then start the system:
```clojure
user=> (go)
;; =>
;; :initiated
```

make some changes and then reset the system:
```clojure
user=> (reset)
;; =>
;; :reloading (app.domain.message app.web.handlers app.web.aleph app.storage.date-time user app.main)
;; :resumed
```

and finally, halt the system:
```clojure
user=> (halt)
;; =>
;; :halted
```

#### Building uberjar
```bash
./scripts/build-uberjar.sh
```

#### Running uberjar

```bash
./scripts/run-uberjar.sh
```

#### How to Test
```bash
curl localhost:3000/api/now

curl localhost:3000/api/ping?feedback=pong

curl localhost:3000/api/add?x=1&y=2
```
