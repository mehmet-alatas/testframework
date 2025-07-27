@API
  Feature: API User Controller Testleri

    @Api_TC01
    Scenario: TC01| Kullanici siteye gecerli datalar ile kayit olabilmelidir
      Given User Register icin URL duzenlenir
      And User Register icin payload duzenlenir
      When User Register icin POST request gonderilir ve response alinir
      Then User Register icin Status kodunun 200 oldugu dogrulanir
      And User Register icin Response body dogrulanir

 @Api_TC02
    Scenario: TC02| Kullanici siteye gecerli datalar ile login olabilmelidir
      Given User login icin URL duzenlenir
      And User login icin payload düzenlenir
      When User login icin POST request gonderilir ve response alinir
      Then User login icin Status kodunun 200 oldugu dogrulanir
      And User login icin Response body dogrulanir

 @Api_TC03
    Scenario: TC03| Kullanici hesabini silebilmelidir
      Given User Hesap silmek icin URL duzenlenir
      When User Hesap silmek icin DELETE request gonderilir ve response alinir
      Then User Hesap silmek icin Status kodunun 200 oldugu dogrulanir








