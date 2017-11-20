<template>
	<div class="row component-functiecontent">

		<section class="content">
			<div class="container">
				<div class="row">

          <div class="step-text col-xs-12 col-sm-8 col-lg-offset-1">
            <h2>Een verzameling van informatie</h2>
            <p>Jouw gegevens, activiteit en vragen (Assignments) staan weergegeven in jouw profiel. Deze zijn te zien door anderen om met jou in contact te komen.<br><br>Wat er op jouw profiel staat kan jij zelf aanpassen door naar de instellingen te gaan. In de instellingen kan jij jouw profielfoto, opleiding/klas en social media accounts aanpassen. Lees meer over <nuxt-link class="lnk lnk-secondary" title="Lees over Communicatie manieren" :to="'/functies/communicatie'">hoe jij in contact komt met anderen.</nuxt-link><br><br>De vragen waar iemand hulp bij wilt zijn ook te vinden op iemand zijn/haar profiel, zo kun je meteen kijken of je bij meerdere vragen kunt helpen. Je kunt een vraag/assignment ook delen om jouw vragen of de vragen van een vriend/vriendin onder de aandacht te brengen in een bestaande groepsgesprek zodat je sneller wordt geholpen. Misschien weet jij wel iemand die bij iemand zijn vraag kan helpen. Lees meer over <nuxt-link class="lnk lnk-secondary" title="Lees over het delen van vragen/assignments" :to="'/functies/delen'">delen van vragen/assignments.</nuxt-link></p>

						<!-- simple link -->
						<!-- <nuxt-link class="lnk lnk-secondary" title="Lees over Notificaties" :to="'/functies/notificaties'">ullamcorper</nuxt-link> -->

						<!-- CTA -->
            <nuxt-link class="lnk lnk-primary" title="Lees meer over vastlopen" :to="'/functies/hashtags'">
							Weten hoe jij sneller geholpen kunt worden?
							<img src="../../assets/img/icon-cta-arrow.svg" alt="Lees meer" class="cta-arrow" draggable="false">
						</nuxt-link>
          </div>

        </div>
      </div>
    </section>

  </div>
</template>
