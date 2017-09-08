<template>
	<div class="row">

    <section class="masthead">
      <masthead-navigation></masthead-navigation>
			<div class="container">
				<div class="row">

					<div class="col-xs-12 col-md-10 col-md-offset-1 text-center">
            <h1>Het middel voor een communicatieve leeromgeving</h1>
          </div>
          <div class="col-xs-12 col-md-8 col-md-offset-2 text-center">
            <h3>Assign helpt bij het creëren van een communicatieve leeromgeving waar studenten zelfstandig en samenwerkend leren doormiddel van een iOS App.</h3>
            <nuxt-link class="btn btn-primary" alt="Vraag een demo versie aan" :to="'#demo'">Vraag een demo versie aan</nuxt-link> <nuxt-link class="btn btn-secundary" alt="Deel met jouw school" :to="'#delen'">Deel met jouw school</nuxt-link>
            <h4>Licht jouw school in door dit te delen.</h4>
          </div>

        </div>
      </div>
    </section>

    <section>
			<div class="container">
				<div class="row">

          <div class="col-xs-12 col-md-5 col-md-offset-1">
            <h5>Label text</h5>
            <h2>Een grote titel met een nog grotere impact</h2>
            <p>Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Sed posuere consectetur.</p>
            <nuxt-link class="btn btn-primary" alt="Vraag een demo versie aan" :to="'#demo'">Lees meer over titels</nuxt-link>
          </div>

        </div>
      </div>
    </section>

    <section>
			<div class="container">
				<div class="row">

          <div class="col-xs-12 col-md-5 col-md-offset-6">
            <h5>Label text</h5>
            <h2>Een grote titel met een nog grotere impact</h2>
            <p>Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Sed posuere consectetur.</p>
            <nuxt-link class="btn btn-primary" alt="Vraag een demo versie aan" :to="'#demo'">Lees meer over titels</nuxt-link>
          </div>

        </div>
      </div>
    </section>

  </div>
</template>


<script>
import MastheadNavigation from '../components/MastheadNavigation.vue'
export default {
  components: {
    MastheadNavigation
  }
}
</script>
