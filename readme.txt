API Testing urls


post: http://localhost:8080/companies/add
{
  "id":"2",
  "branchName": "ABCDE Company",
  "companyplace":"abcd",
  "place": "Andaman",
  "website": "www.example.com",
  "email": "test@example.com",
  "contact": "1234567890",
  "tarrif":"60000"
}

get:http://localhost:8080/companies/get

put:http://localhost:8080/companies/branch/updatetariff/94569
{
    "tariff":"70000"
}