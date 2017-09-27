<template>
	<div class="row component-footerpage">

		<footer>
			<div class="container">
				<div class="row">

					<div class="col-xs-12 col-sm-2 col-md-2 col-lg-offset-1 footer-section">
						<b>Hulp</b>
						<ul>
							<li><nuxt-link class="lnk" title="Contact" :to="'/contact'">Contact</nuxt-link></li>
							<li><a class="lnk" title="Twitter" target="_blank" href="https://twitter.com/assignappnl">Twitter</a></li>
							<li><a class="lnk" title="Mail" href="mailto:assignapp%2edev%40gmail%2ecom">Mail</a></li>
							<!-- <li><nuxt-link class="lnk" title="Vragen" :to="'/faq'">Vragen</nuxt-link></li> -->
						</ul>
					</div>

					<div class="col-xs-12 col-sm-2 col-md-2 footer-section">
						<b>Legal</b>
						<ul>
							<!-- <li><nuxt-link class="lnk" title="Voorwaarden" :to="'/voorwaarden'">Voorwaarden</nuxt-link></li> -->
							<!-- <li><nuxt-link class="lnk" title="Privacy" :to="'/privacy'">Privacy</nuxt-link></li> -->
							<!-- <li><a class="lnk" title="Perskit" target="_blank" href="#">Perskit</a></li> -->
							<li><a class="lnk" title="Release Notes" target="_blank" href="https://headwayapp.co/assign-app-changelog">Release Notes</a></li>
							<li class="headway-widget"></li>
						</ul>
					</div>

					<div class="col-xs-12 col-sm-2 col-md-3 col-lg-2 footer-section">
						<b>Functies</b>
						<ul>
							<li><nuxt-link class="lnk" title="Lees over Hashtags" :to="'/functies/hashtags'">Hashtags</nuxt-link></li>
							<li><nuxt-link class="lnk" title="Lees over Profielen" :to="'/functies/profiel'">Profielen</nuxt-link></li>
							<li><nuxt-link class="lnk" title="Lees over Notificaties" :to="'/functies/notificaties'">Notificaties</nuxt-link></li>
							<li><nuxt-link class="lnk lnk-dropdown" title="Lees over Communicatie manieren" :to="'/functies/communicatie'">Communicatie manieren</nuxt-link></li>
						</ul>
					</div>


					<div class="col-xs-12 col-sm-6 col-md-5 footer-section">
						<b>Blijf up to date</b>
						<ul>
							<li><div class="lnk">Wij delen ons proces in ons nieuwsbrief.</div></li>
						</ul>
						<!-- Begin MailChimp Signup Form -->
						<div id="mc_embed_signup">
						<form action="//assignapp.us16.list-manage.com/subscribe/post?u=06ec3ad60c8473d5c82f6f977&amp;id=2d6c899cf6" method="post" id="mc-embedded-subscribe-form" name="mc-embedded-subscribe-form" class="validate" target="_blank" novalidate>
					    <div id="mc_embed_signup_scroll">
								<div class="mc-field-group">
									<input type="email" value="" placeholder="Vul jouw e-mailadres in" name="EMAIL" class="required email" id="mce-EMAIL">
								</div>
								<div id="mce-responses" class="clear">
									<div class="response" id="mce-error-response" style="display:none"></div>
									<div class="response" id="mce-success-response" style="display:none"></div>
								</div>
								<!-- real people should not fill this in and expect good things - do not remove this or risk form bot signups-->
						    <div style="position: absolute; left: -5000px;" aria-hidden="true"><input type="text" name="b_06ec3ad60c8473d5c82f6f977_2d6c899cf6" tabindex="-1" value=""></div>
						    <div class="clear"><input type="submit" value="Ga verder" name="subscribe" id="mc-embedded-subscribe" class="button"></div>
					    </div>
						</form>
						</div>
						<!--End mc_embed_signup-->
					</div>

				</div>
			</div>
		</footer>

	</div>
</template>
