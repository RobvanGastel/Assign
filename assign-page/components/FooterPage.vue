<template>
	<div class="row">

		<footer>
			<div class="container">
				<div class="row">

					<div class="col-xs-12 col-sm-4 col-md-2 col-md-offset-1 footer-section">
						<b>Hulp</b>
						<ul>
							<li><nuxt-link class="lnk" alt="Contact" :to="'/contact'">Contact</nuxt-link></li>
							<li><nuxt-link class="lnk" alt="Twitter" :to="'https://twitter.com/assignappnl'">Twitter</nuxt-link></li>
							<li><nuxt-link class="lnk" alt="Vragen" :to="'mailto:assignapp.dev@gmail.com'">Vragen</nuxt-link></li>
							<li><nuxt-link class="lnk" alt="Vragen" :to="'/faq'">Vragen</nuxt-link></li>
						</ul>
					</div>

					<div class="col-xs-12 col-sm-4 col-md-2 footer-section">
						<b>Functies</b>
						<ul>
							<li><nuxt-link class="lnk" alt="Contact" :to="'/contact'">Bellen</nuxt-link></li>
							<li><nuxt-link class="lnk" alt="Twitter" :to="'https://twitter.com/assignappnl'">Appen</nuxt-link></li>
							<li><nuxt-link class="lnk" alt="Vragen" :to="'mailto:assignapp.dev@gmail.com'">SMS’n</nuxt-link></li>
							<li><nuxt-link class="lnk" alt="Vragen" :to="'/faq'">Mailen</nuxt-link></li>
						</ul>
					</div>

					<div class="col-xs-12 col-sm-4 col-md-2 footer-section">
						<b>Legal</b>
						<ul>
							<li><nuxt-link class="lnk" alt="Contact" :to="'/contact'">Voorwaarden</nuxt-link></li>
							<li><nuxt-link class="lnk" alt="Twitter" :to="'https://twitter.com/assignappnl'">Privacy</nuxt-link></li>
							<li><nuxt-link class="lnk" alt="Vragen" :to="'mailto:assignapp.dev@gmail.com'">Pers</nuxt-link></li>
							<li><nuxt-link class="lnk" alt="Vragen" :to="'/faq'">Release Notes</nuxt-link></li>
						</ul>
					</div>

				</div>
			</div>
		</footer>

	</div>
</template>
