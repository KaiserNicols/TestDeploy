import { Component, OnInit } from '@angular/core';
import { Recommendation } from '../recommendation';
import { RecommendationService } from '../recommendation.service';

@Component({
  selector: 'app-charts',
  templateUrl: './charts.component.html',
  styleUrls: ['./charts.component.css']
})
export class ChartsComponent implements OnInit {
  
  personalRecommendations: Recommendation[];
  groupRecommendations: Recommendation[];
  
  personalChartTitle = "Recommendations for you";
  personalChartType = "PieChart";
  personalChartData = [ ["Helpful", 0], ["Unhelpful", 0] ];
  personalChartColNames = ['Status', 'Count'];
  personalChartOptions = {
    'width': 400,
    'height': 300
  };

  groupChartTitle = "Recommendations for all users";
  groupChartType = "BarChart";
  groupChartData = [ ["Helpful", 0], ["Unhelpful", 0] ];
  groupChartColNames = ['Status', 'Count'];
  groupChartOptions = {
    'width': 400,
    'height': 300
  };
  
  constructor(private recommendationService: RecommendationService) {}

  ngOnInit() {
    this.getRecommendations();
    this.getAllRecommendations();
  }

  getRecommendations(): void {
    this.recommendationService.getRecommendations()
        .subscribe(recommendations => 
          {
          console.log(recommendations);
          this.personalRecommendations = recommendations;
          this.setPersonalChartData();
          }
        );
      
  }

  getAllRecommendations(): void {
    this.recommendationService.getAllRecommendations()
        .subscribe(recommendations => 
          {
          console.log(recommendations);
          this.groupRecommendations = recommendations
          this.setGroupChartData();
          }
        );
  }

  setPersonalChartData() : void {
    let helpfulCount = 0;
    let unhelpfulCount = 0;
    for (let i=0; i<this.personalRecommendations.length; i++) {
      if (this.personalRecommendations[i].helpful) {
        helpfulCount++;
      } else {
        unhelpfulCount++;
      }
    }
    this.personalChartData = [ ["Helpful", helpfulCount], ["Unhelpful", unhelpfulCount] ];
  }

  setGroupChartData() : void {
    let helpfulCount = 0;
    let unhelpfulCount = 0;
    for (let i=0; i<this.groupRecommendations.length; i++) {
      if (this.groupRecommendations[i].helpful) {
        helpfulCount++;
      } else {
        unhelpfulCount++;
      }
    }
    this.groupChartData = [ ["Helpful", helpfulCount], ["Unhelpful", unhelpfulCount] ];
  }
}
