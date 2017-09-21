<template>
	<div class="row component-modallicense">

		<section class="modal" id="modal-license">
			<div class="container">
				<div class="row">

          <div class="col-xs-12 col-sm-10 col-md-8 col-lg-6 col-sm-offset-1 col-md-offset-2 col-lg-offset-3">

						<div class="modal-license">
							<div class="modal-license-header">
								<img src="../assets/img/close.svg" alt="Sluiten" id="close-modal-license" draggable="false">
		            <h5>Beta licentie aanvragen</h5>
		            <h2>Vul hier de gegevens in om een licentie aan te vragen.</h2>
							</div>

							<div class="modal-license-content">
		            <p>Wij zullen een mailtje sturen met de benodigde informatie om de app te gaan gebruiken in jullie school omgeving.</p>

								<form>
									<label for="naam">naam</label>
									<input type="text" name="naam" id="naam" placeholder="Vul hier uw naam in" class="modal-inputtext"/>

									<label for="mail">E-mail</label>
									<input type="text" name="mail" id="mail" placeholder="Vul hier uw e-mailadres in" class="modal-inputtext"/>

									<label for="school">schoolnaam</label>
									<input type="text" name="school" id="school" placeholder="Vul hier de naam van uw school in" class="modal-inputtext"/>

									<label>School grootte</label>
									<div class="modal-radiobox">
										<input type="radio" name="grootte" id="grootte_1" value="tot 500" checked>
										<label for="grootte_1">
											<img src="../assets/img/checkbox.svg" alt="Ik accepteer de Gebruiksvoorwaarden" class="icon-checkbox">
											<img src="../assets/img/checkedbox.svg" alt="Ik accepteer de Gebruiksvoorwaarden" class="icon-checkedbox">
											tot 500
										</label>
									</div>

									<div class="modal-radiobox modal-radiobox-center">
										<input type="radio" name="grootte" id="grootte_2" value="500 - 1500">
										<label for="grootte_2">
											<img src="../assets/img/checkbox.svg" alt="Ik accepteer de Gebruiksvoorwaarden" class="icon-checkbox">
											<img src="../assets/img/checkedbox.svg" alt="Ik accepteer de Gebruiksvoorwaarden" class="icon-checkedbox">
											500 - 1500
										</label>
									</div>

									<div class="modal-radiobox">
										<input type="radio" name="grootte" id="grootte_3" value="1500+">
										<label for="grootte_3">
											<img src="../assets/img/checkbox.svg" alt="Ik accepteer de Gebruiksvoorwaarden" class="icon-checkbox">
											<img src="../assets/img/checkedbox.svg" alt="Ik accepteer de Gebruiksvoorwaarden" class="icon-checkedbox">
											1500+
										</label>
									</div>

			            <nuxt-link class="btn btn-primary" type="submit" title="Vraag een licentie aan" :to="'#demo'">Vraag een licentie aan</nuxt-link>

									<label class="modal-checkbox">
										<input type="checkbox" required="true" name="voorwaarden"/>
										<img src="../assets/img/checkedbox.svg" alt="Ik accepteer de Gebruiksvoorwaarden" class="icon-checkedbox">
										<img src="../assets/img/checkbox.svg" alt="Ik accepteer de Gebruiksvoorwaarden" class="icon-checkbox">
									</label>
									<p class="voorwaarden-text">Ik heb de <nuxt-link title="Bekijk de Gebruiksvoorwaarden" :to="'/gebruiksvoorwaarden'">Gebruiksvoorwaarden</nuxt-link> gelezen</p>

								</form>

							</div>

						</div>

          </div>

        </div>
      </div>
    </section>

  </div>
</template>
