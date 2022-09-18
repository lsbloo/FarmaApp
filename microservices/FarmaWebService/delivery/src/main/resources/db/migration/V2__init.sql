INSERT INTO item_onboarding(id,description,image,label_button,title) VALUES (1,'Encontre medicamentos e produtos farmâceuticos sem sair de casa. Venha conferir nossas ofertas.','images/onboarding_screen_1.png','Continuar','Bem vindo ao FarmaApp');
INSERT INTO item_onboarding(id,description,image,label_button,title) VALUES (2,'Com o FarmaApp você poderá visualizar e comprar produtos online e o pedido chegará na sua casa!','images/onboarding_screen_2.png','Começar agora!','Produtos entregues na sua região!');
INSERT INTO onboardingdto(id) VALUES (1);
INSERT INTO onboardingdto_onboarding_screen(onboardingdto_id, onboarding_screen_id) VALUES (1,1);
INSERT INTO onboardingdto_onboarding_screen(onboardingdto_id, onboarding_screen_id) VALUES (1,2);
