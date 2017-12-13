<template>
	<div class="row component-download white-bg section-padding">

		<section class="download">
			<div class="container">
				<div class="row">

          <div class="col-xs-12 col-md-10 col-lg-8 col-md-offset-1 col-lg-offset-2 text-center">
            <h5>Begin anderen te helpen</h5>
            <h2>Download de app</h2>
            <p>Als jouw school een Assign licentie heeft kan jij de app downloaden in de App Store voor iPhone. Heeft jouw school geen licentie? Laat jouw school dit weten.</p>
            <nuxt-link class="btn btn-primary" title="Download de app" :to="'#download'">Download de iOS App</nuxt-link>
						<button v-clipboard:copy="clipboardMessage" class="btn btn-secundary hidden-xs" title="Deel de gekopieerde link met jouw school" v-on:click="setNewValue">{{ btnMessage }}</button>
          </div>

        </div>
      </div>
    </section>

  </div>
</template>

<script>
export default {
  data: () => ({
    showModal: false,
    clipboardMessage: 'https://assignapp.nl/voor-scholen',
    btnMessage: 'Deel met jouw school'
  }),
  methods: {
    setNewValue: function () {
      this.btnMessage = 'De link is gekopieerd'
    }
  }
}
</script>
