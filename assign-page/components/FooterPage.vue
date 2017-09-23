<template>
	<div class="row component-footerpage">

		<footer>
			<div class="container">
				<div class="row">

					<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2 col-lg-offset-1 footer-section">
						<b>Hulp</b>
						<ul>
							<li><nuxt-link class="lnk" title="Contact" :to="'/contact'">Contact</nuxt-link></li>
							<li><a class="lnk" title="Twitter" target="_blank" href="https://twitter.com/assignappnl">Twitter</a></li>
							<li><a class="lnk" title="Mail" href="mailto:assignapp%2edev%40gmail%2ecom">Mail</a></li>
							<li><nuxt-link class="lnk" title="Vragen" :to="'/faq'">Vragen</nuxt-link></li>
							<!-- <li class="headway-widget"></li> -->
						</ul>
					</div>

					<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2 footer-section">
						<b>Functies</b>
						<ul>
							<li><nuxt-link class="lnk" title="Lees over Hashtags" :to="'/functies/hashtags'">Hashtags</nuxt-link></li>
							<li><nuxt-link class="lnk" title="Lees over Profielen" :to="'/functies/profiel'">Profielen</nuxt-link></li>
							<li><nuxt-link class="lnk" title="Lees over Notificaties" :to="'/functies/notificaties'">Notificaties</nuxt-link></li>
							<li><nuxt-link class="lnk lnk-dropdown" title="Lees over Communicatie manieren" :to="'/functies/communicatie'">Communicatie manieren</nuxt-link></li>
						</ul>
					</div>

					<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2 footer-section">
						<b>Legal</b>
						<ul>
							<li><nuxt-link class="lnk" title="Contact" :to="'/#contact'">Voorwaarden</nuxt-link></li>
							<li><a class="lnk" title="Twitter" target="_blank" href="https://twitter.com/assignappnl">Privacy</a></li>
							<li><a class="lnk" title="Vragen" href="mailto:assignapp%2edev%40gmail%2ecom">Pers</a></li>
							<li><nuxt-link class="lnk" title="Vragen" :to="'/#faq'">Release Notes</nuxt-link></li>
						</ul>
					</div>

				</div>
			</div>
		</footer>

	</div>
</template>
