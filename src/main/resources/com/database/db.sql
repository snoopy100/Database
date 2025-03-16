
CREATE TABLE IF NOT EXISTS myTable (
    Date VARCHAR(255),
    Name VARCHAR(255),
    MRN VARCHAR(255),
    ETM_Visit VARCHAR(16),
    Proc VARCHAR(100),
    Procedure_Notes VARCHAR(10000)
);

-- SELECT * FROM CSVREAD('file:/Users/jacksonkotch/Desktop/Database/src/main/resources/com/database/EP Coding Feb 2025.csv', null, 'header = true');