DROP TABLE IF EXISTS swallow;
CREATE TABLE IF NOT EXISTS swallow (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    index_number INTEGER NOT NULL ,
    date TEXT NOT NULL ,
    longitude REAL NOT NULL ,
    latitude REAL NOT NULL ,
    longitude_difference REAL NOT NULL ,
    latitude_difference REAL NOT NULL ,
    distance REAL NOT NULL,
    table_number INTEGER,
    FOREIGN KEY (table_number) REFERENCES swallow_table(id)
);

CREATE TABLE IF NOT EXISTS swallow_table(
    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ,
    name TEXT NOT NULL UNIQUE
);