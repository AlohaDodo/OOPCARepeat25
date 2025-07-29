CREATE TABLE `DONOR` (
    `ID` int(4) NOT NULL,
    `FIRST_NAME` varchar(30) NOT NULL,
    `SECOND_NAME` varchar(30) NOT NULL,
    `TELENUMBER` varchar(11) NOT NULL,
    PRIMARY KEY (`ID`)
);

CREATE TABLE `ANIMAL` (
    `ID` int(4) NOT NULL,
    `TYPE` varchar(30) NOT NULL,
    `BREED` varchar(30) NOT NULL,
    `NAME` varchar(30) NOT NULL,
    `AGE` int(11) NOT NULL,
    `WEIGHT` float(5,2) NOT NULL,
    `NEUTERED` tinyint(1) NOT NULL,
    `HEALTH` varchar(30) NOT NULL,
    `ADMITTED` date NOT NULL,
    `GENDER` varchar(30) NOT NULL,
    `DONOR_ID` int(4) NOT NULL,
    PRIMARY KEY (`ID`),
    FOREIGN KEY (`DONOR_ID`) REFERENCES `DONOR`(`ID`)
);

INSERT INTO `DONOR` (`ID`, `FIRST_NAME`, `SECOND_NAME`, `TELENUMBER`) VALUES
(1, 'John', 'OSullivan', '0871234567'),
(2, 'Mary', 'McCarthy', '0852345678'),
(3, 'Patrick', 'OBrien', '0861112233'),
(4, 'Siobhan', 'Murphy', '0872223344'),
(5, 'Michael', 'Doyle', '0833334455'),
(6, 'Emma', 'OConnell', '0841234567'),
(7, 'Sean', 'Fitzpatrick', '0851112222'),
(8, 'Aisling', 'Byrne', '0872345678'),
(9, 'Liam', 'Gallagher', '0861234890'),
(10, 'Niamh', 'Kennedy', '0891234567');

INSERT INTO `ANIMAL` (`ID`, `TYPE`, `BREED`, `NAME`, `AGE`, `WEIGHT`, `NEUTERED`, `HEALTH`, `ADMITTED`, `GENDER`, `DONOR_ID`) VALUES
(1, 'Dog', 'Cockapoo', 'Teddy', 1, 5.20, 0, 'Good', '2025-01-10', 'Boy', 1),
(2, 'Cat', 'British Short Hair', 'Mickey', 4, 4.10, 0, 'Good', '2025-01-02', 'Boy', 2),
(3, 'Cat', 'Ragdoll', 'Topsy', 3, 3.85, 0, 'Bad', '2025-02-03', 'Girl', 3),
(4, 'Dog', 'Labrador', 'Schnappi', 7, 28.50, 0, 'Bad', '2025-01-29', 'Boy', 4),
(5, 'Cat', 'Bombay', 'Wojtek', 2, 4.00, 0, 'Good', '2025-01-10', 'Boy', 5),
(6, 'Dog', 'Labrador', 'Pudding', 3, 26.70, 1, 'Good', '2025-02-28', 'Girl', 6),
(7, 'Dog', 'Bulldog', 'Jack', 2, 22.40, 0, 'Good', '2025-02-28', 'Boy', 7),
(8, 'Cat', 'Siamese', 'Snow', 1, 3.60, 1, 'Bad', '2025-02-03', 'Girl', 8),
(9, 'Dog', 'Husky', 'Tasha', 3, 24.90, 1, 'Good', '2025-02-10', 'Girl', 9),
(10, 'Dog', 'Golden Retriever', 'Lucy', 4, 29.00, 0, 'Good', '2025-02-24', 'Girl', 10);