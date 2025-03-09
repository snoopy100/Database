
CREATE TABLE IF NOT EXISTS myTable (
    Date VARCHAR(255),
    Name VARCHAR(255),
    MRN VARCHAR(255),
    Procedure_Notes VARCHAR(10000),
    ETM_Visit INT
);

-- SELECT * FROM CSVREAD('file:/Users/jacksonkotch/Desktop/Database/src/main/resources/com/database/EP Coding Feb 2025.csv', null, 'header = true');